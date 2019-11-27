package monopoly.rendering;

import org.joml.Vector4f;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.*;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.assimp.Assimp.*;

public class StaticMeshesLoader {

    public static Mesh[] load(String resourcePath, String texturesDir) throws Exception {
        return load(resourcePath, texturesDir,
                aiProcess_JoinIdenticalVertices |
                        aiProcess_Triangulate |
                        aiProcess_FixInfacingNormals |
                        aiProcess_CalcTangentSpace
        );
    }

    public static Mesh[] load(String resourcePath, String texturesDir, int flags) throws Exception {
        try (AIScene aiScene = aiImportFile(resourcePath, flags)) {
            if (aiScene == null) {
                throw new RuntimeException("Error loading model: " + resourcePath + " : " + aiGetErrorString());
            }
            int numMaterials = aiScene.mNumMaterials();
            PointerBuffer aiMaterials = aiScene.mMaterials();
            List<Material> materials = new ArrayList<>();
            for (int i = 0; i < numMaterials; i++) {
                AIMaterial aiMaterial = AIMaterial.create(aiMaterials.get(i));
                processMaterial(aiMaterial, materials, texturesDir);
            }

            int numMeshes = aiScene.mNumMeshes();
            PointerBuffer aiMeshes = aiScene.mMeshes();
            Mesh[] meshes = new Mesh[numMeshes];
            for (int i = 0; i < numMeshes; i++) {
                AIMesh aiMesh = AIMesh.create(aiMeshes.get(i));
                Mesh mesh = processMesh(aiMesh, materials);
                meshes[i] = mesh;
            }
            return meshes;
        }
    }

    private static void processMaterial(AIMaterial aiMaterial, List<Material> materials, String texturesDir) throws Exception {
        AIColor4D color = AIColor4D.create();

        Texture texture = null;
        Texture normalTexture = null;
        for (int i = 0; i <= 12; i++) {
            System.out.print(Assimp.aiGetMaterialTextureCount(aiMaterial, i) + " ");
        }
        System.out.println();

        try (AIString path = AIString.calloc()) {
            Assimp.aiGetMaterialTexture(aiMaterial, aiTextureType_DIFFUSE, 0, path, (IntBuffer) null,
                    null, null, null, null, null
            );

            String textPath = path.dataString();
            if (textPath != null && textPath.length() > 0) {
                TextureCache textureCache = TextureCache.getInstance();
                String textureFile = "";
                if (texturesDir != null && texturesDir.length() > 0) {
                    textureFile += texturesDir;
                }
                textureFile += textPath;
                textureFile = textureFile.replace("\\\\", "/");
                texture = textureCache.getTexture(textureFile);
            }
        }
        try (AIString path = AIString.calloc()) {
            Assimp.aiGetMaterialTexture(aiMaterial, aiTextureType_NORMALS, 0, path, (IntBuffer) null,
                    null, null, null, null, null
            );
            String textPath = path.dataString();
            if (textPath != null && textPath.length() > 0) {
                TextureCache textureCache = TextureCache.getInstance();
                String textureFile = "";
                if (texturesDir != null && texturesDir.length() > 0) {
                    textureFile += texturesDir;
                }
                textureFile += textPath;
                textureFile = textureFile.replace("\\\\", "/");
                normalTexture = textureCache.getTexture(textureFile);
            } else {
                Assimp.aiGetMaterialTexture(aiMaterial, aiTextureType_HEIGHT, 0, path, (IntBuffer) null,
                        null, null, null, null, null
                );
                textPath = path.dataString();
                if (textPath != null && textPath.length() > 0) {
                    TextureCache textureCache = TextureCache.getInstance();
                    String textureFile = "";
                    if (texturesDir != null && texturesDir.length() > 0) {
                        textureFile += texturesDir;
                    }
                    textureFile += textPath;
                    textureFile = textureFile.replace("\\\\", "/");
                    normalTexture = textureCache.getTexture(textureFile);
                }
            }
        }


        Vector4f ambient = Material.DEFAULT_COLOR;
        int result = aiGetMaterialColor(aiMaterial, AI_MATKEY_COLOR_AMBIENT, aiTextureType_NONE, 0, color);
        if (result == 0) {
            ambient = new Vector4f(color.r(), color.g(), color.b(), color.a());
        }

        Vector4f diffuse = Material.DEFAULT_COLOR;
        result = aiGetMaterialColor(aiMaterial, AI_MATKEY_COLOR_DIFFUSE, aiTextureType_NONE, 0, color);
        if (result == 0) {
            diffuse = new Vector4f(color.r(), color.g(), color.b(), color.a());
        }

        Vector4f specular = Material.DEFAULT_COLOR;
        result = aiGetMaterialColor(aiMaterial, AI_MATKEY_COLOR_SPECULAR, aiTextureType_NONE, 0, color);
        if (result == 0) {
            specular = new Vector4f(color.r(), color.g(), color.b(), color.a());
        }

        Material material = new Material(ambient, diffuse, specular, 1.0f);
        material.setTexture(texture);
        material.setNormalMap(normalTexture);
        materials.add(material);
    }

    private static Mesh processMesh(AIMesh aiMesh, List<Material> materials) {
        List<Float> vertices = new ArrayList<>();
        List<Float> textures = new ArrayList<>();
        List<Float> normals = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        List<Float> tangents = new ArrayList<>();

        processVertices(aiMesh, vertices);
        processNormals(aiMesh, normals);
        processTextureCoords(aiMesh, textures);
        processIndices(aiMesh, indices);
        processTangents(aiMesh, tangents);

        Mesh mesh = new Mesh(
                listToArray(vertices),
                listToArray(textures),
                listToArray(normals),
                listToIntArray(indices),
                listToArray(tangents)
        );
        Material material;
        int materialIndex = aiMesh.mMaterialIndex();
        if (materialIndex >= 0 && materialIndex < materials.size()) {
            material = materials.get(materialIndex);
        } else {
            material = new Material();
        }
        mesh.setMaterial(material);

        return mesh;
    }

    private static void processVertices(AIMesh aiMesh, List<Float> vertices) {
        AIVector3D.Buffer aiVertices = aiMesh.mVertices();
        while (aiVertices.remaining() > 0) {
            AIVector3D aiVertex = aiVertices.get();
            vertices.add(aiVertex.x());
            vertices.add(aiVertex.y());
            vertices.add(aiVertex.z());
        }
    }

    private static void processNormals(AIMesh aiMesh, List<Float> normals) {
        AIVector3D.Buffer aiNormals = aiMesh.mNormals();
        while (aiNormals != null && aiNormals.remaining() > 0) {
            AIVector3D aiNormal = aiNormals.get();
            normals.add(aiNormal.x());
            normals.add(aiNormal.y());
            normals.add(aiNormal.z());
        }
    }

    private static void processTextureCoords(AIMesh aiMesh, List<Float> textureCoords) {
        AIVector3D.Buffer aiTextureCoords = aiMesh.mTextureCoords(0);
        while (aiTextureCoords != null && aiTextureCoords.remaining() > 0) {
            AIVector3D textureCoord = aiTextureCoords.get();
            textureCoords.add(textureCoord.x());
            textureCoords.add(1 - textureCoord.y());
        }
    }

    private static void processIndices(AIMesh aiMesh, List<Integer> indices) {
        int numFaces = aiMesh.mNumFaces();
        AIFace.Buffer aiFaces = aiMesh.mFaces();
        for (int i = 0; i < numFaces; i++) {
            AIFace aiFace = aiFaces.get(i);
            IntBuffer buffer = aiFace.mIndices();
            while (buffer.remaining() > 0) {
                indices.add(buffer.get());
            }
        }
    }

    private static void processTangents(AIMesh aiMesh, List<Float> tangents) {
        AIVector3D.Buffer aiTangents = aiMesh.mTangents();
        while (aiTangents != null && aiTangents.remaining() > 0) {
            AIVector3D tangent = aiTangents.get();
            tangents.add(tangent.x());
            tangents.add(tangent.y());
            tangents.add(tangent.z());
        }
    }

    public static float[] listToArray(List<Float> list) {
        float[] result = new float[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static int[] listToIntArray(List<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
