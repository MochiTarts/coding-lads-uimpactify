# project-coding-lads

### When building the project, create a new run configuration by:
1. Click dropdown arrow beside green play button
2. Select run configurations
3. In the left panel, scroll down to "Maven Build"
4. Right click and select "new configuration"
5. Name the configuration "clean install"
6. in Base directory field, click "Variables..." button and select "project_loc"
7. in Goals field, type "clean install"
8. In the checkboxes below, click "Skip Tests" to be true
9. Left click the project at the root level, then click your new run config to do a clean install


### Run the spring application
After building the project, do the following to run the spring application
1. Right click the root project, and select show in local terminal.  Alternatively, left click the root project and hit ctrl+alt+t
2. Type "mvn spring-boot:run"
3. Wait until you see the giant SPRING logo and it says that your server is up and running, hit ctrl+c to kill it.

### Running the front-end
Go to "project-coding-lads\frontend\uimpactify-web"
With node installed (https://nodejs.org/en/), run:
1. npm install
2. npm start  
The first command installs all dependencies.
The second command runs the react script after which, a new browser window should open the website on localhost.
