# Monopoly - CIS 18C Final Project
## **Setting up Project:**  

I highly recommend that you use IntelliJ IDEA for this project instead of netbeans.  
The version of netbeans that we use is a pain to set up with this project.  

### Installation:
1. Download IntelliJ IDEA: https://www.jetbrains.com/idea/download/#section=windows
2. Download Git: https://git-scm.com/downloads
3. Install both - you should be able to click next through all options.

### Cloning:
1. Open IntelliJ IDEA
2. Close your project if you have one open already (`File -> Close Project`)
3. Click `Check out from version control`
4. Select `Git`
5. Go to your github account on the github website, and go to your fork
6. Click `Clone or download`, and copy the link
7. Go back to IntelliJ, and paste the link in the box
8. Click `Login to Github` and login with your github account
9. Click `Clone`
10. Click Yes to open the project
11. Go to `File -> Settings`
12. Go to `Build, Execution, Deployment -> Build Tools -> Maven -> Importing`
13. Enable `Import Maven Projects Automatically`
14. Under `Automatically Download`, enable `Documentation`
15. Go to `VCS -> Git -> Remotes`
16. Click the `+` and enter `upstream` for the name
17. Enter https://github.com/eafigal/monopoly.git for the url

### Updating your fork:
##### Make sure all changes you've made are committed:
1. Go to `VCS -> Commit` or press `Control + K`
2. Describe your changes in the commit message
3. Click `Commit`  
##### Pull changes from upstream:
1. Go to `VCS -> Git -> Pull`
2. In the `remote` option, select `upstream`
3. Select `upstream/master`  
(If you don't see any options, click the refresh button)
4. Click `Pull`   
##### Update your github fork:
1. Go to `VCS -> Git -> Push`
2. Click `Push`

Everything with your fork should be up to date with the latest code now!

### Pull Requests:
Coming soon!