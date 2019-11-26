#version 330

in vec2 outTexCoord;
in vec3 mvVertexNormal;
in vec3 mvVertexPos;
in mat3 TBN;

out vec4 fragColor;

struct Attenuation
{
    float constant;
    float linear;
    float exponent;
};

struct PointLight
{
    vec3 color;
    vec3 position;
    float intensity;
    Attenuation att;
};

struct DirectionalLight
{
    vec3 color;
    vec3 direction;
    float intensity;
};

struct SpotLight
{
    PointLight pl;
    vec3 conedir;
    float cutoff;
};

struct Material
{
    vec4 ambient;
    vec4 diffuse;
    vec4 specular;
    int hasTexture;
    int hasNormalMap;
    float reflectance;
};

const int MAX_POINT_LIGHTS = 2;
const int MAX_SPOT_LIGHTS = 1;

uniform sampler2D texture_sampler;
uniform sampler2D normalMap;
uniform vec3 ambientLight;
uniform float specularPower;
uniform Material material;
uniform PointLight pointLights[MAX_POINT_LIGHTS];
uniform DirectionalLight directionalLight;
uniform SpotLight spotLights[MAX_SPOT_LIGHTS];

vec4 ambientC;
vec4 diffuseC;
vec4 specularC;

void setupColors(Material material, vec2 textCoord)
{
    if (material.hasTexture == 1)
    {
        ambientC = texture(texture_sampler, textCoord);
        diffuseC = ambientC;
        specularC = ambientC;
    }
    else
    {
        ambientC = material.ambient;
        diffuseC = material.diffuse;
        specularC = material.specular;
    }
}

vec4 calcLightColor(vec3 light_color, float light_intensity, vec3 position, vec3 to_light_dir, vec3 normal)
{
    vec4 diffuseColor = vec4(0, 0, 0, 0);
    vec4 specColor = vec4(0, 0, 0, 0);

    float diffuseFactor = max(dot(normal, to_light_dir), 0.0);
    diffuseColor = diffuseC * vec4(light_color, 1.0) * light_intensity * diffuseFactor;

    vec3 camera_direction = normalize(-position);
    vec3 from_light_dir = -to_light_dir;
    vec3 reflected_light = normalize(reflect(from_light_dir, normal));
    float specularFactor = max(dot(camera_direction, reflected_light), 0.0);
    specularFactor = pow(specularFactor, specularPower);
    specColor = specularC * light_intensity * specularFactor * material.reflectance * vec4(light_color, 1);

    return (diffuseColor + specColor);
}

vec4 calcPointLight(PointLight light, vec3 position, vec3 normal)
{
    vec4 diffuseColor = vec4(0, 0, 0, 0);
    vec4 specColor = vec4(0, 0, 0, 0);

    vec3 light_direction = light.position - position;
    vec3 to_light_source = normalize(light_direction);
    vec4 light_color = calcLightColor(light.color, light.intensity, position, to_light_source, normal);

    float distance = length(light_direction);
    float attenuationInv = light.att.constant + light.att.linear * distance + light.att.exponent * distance * distance;
    return (light_color) / attenuationInv;
}

vec4 calcDirectionalLight(DirectionalLight light, vec3 position, vec3 normal)
{
    return calcLightColor(light.color, light.intensity, position, normalize(light.direction), normal);
}

vec4 calcSpotLight(SpotLight light, vec3 position, vec3 normal)
{
    vec3 light_direction = light.pl.position - position;
    vec3 to_light_dir = normalize(light_direction);
    vec3 from_light_dir = -to_light_dir;
    float spot_alpha = dot(from_light_dir, normalize(light.conedir));

    vec4 color = vec4(0, 0, 0, 0);

    if (spot_alpha > light.cutoff)
    {
        color = calcPointLight(light.pl, position, normal);
        color *= (1.0 - (1.0 - spot_alpha) / (1.0 - light.cutoff));
    }
    return color;
}

vec3 calcNormal(Material material, vec3 normal, vec2 text_coord)
{
    vec3 newNormal = normal;
    if (material.hasNormalMap == 1)
    {
        newNormal = texture(normalMap, text_coord).rgb;
        newNormal = normalize(newNormal * 2 - 1);
        newNormal = normalize(TBN * newNormal);
    }
    return newNormal;
}

void main()
{
    setupColors(material, outTexCoord);

    vec3 currentNormal = calcNormal(material, mvVertexNormal, outTexCoord);

    vec4 diffuseSpecularComp = calcDirectionalLight(directionalLight, mvVertexPos, currentNormal);
    for (int i=0; i < MAX_POINT_LIGHTS; i++)
    {
        if (pointLights[i].intensity > 0)
        {
            diffuseSpecularComp += calcPointLight(pointLights[i], mvVertexPos, currentNormal);
        }
    }
    for (int i=0; i < MAX_SPOT_LIGHTS; i++)
    {
        if (spotLights[i].pl.intensity > 0)
        {
            diffuseSpecularComp += calcSpotLight(spotLights[i], mvVertexPos, currentNormal);
        }
    }

    fragColor = ambientC * vec4(ambientLight, 1) + diffuseSpecularComp;
}
