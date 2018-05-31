# Hidden Surface Removal 

 This program reads a .txt file, and fills a polygon (in the case of this project, a cube)
 based on the vertices and coordinates given in the following format:



* (number of vertices + 1 for the homogeneous coordinates)
* vertices 
     * x, y, z
* edges
  * v, w
* faces
  * vertex1, vertex2, vertex3, vertex4...


An example is provided in this repository: 
* (https://github.com/gioflores24/hidden-surface-removal/tree/master/cube.txt)

---------------------------

After creating the .txt file, it needs to be loaded by pressing "Read File" in the GUI after running the main program. This will open up a file dialog to search your files for the .txt file of choice.  

Once the .txt file is loaded, the GUI should look something like this:

* (https://github.com/gioflores24/hidden-surface-removal/tree/master/assets/images/gui.png)



Afterwards, the user can change the translation, scale, and rotation values. Rotation is supported in the x, y and z-axes, as well as any arbitrary axes that the user may want to specify. 

Here's an example of what it might look like: 

* (https://github.com/gioflores24/hidden-surface-removal/tree/master/assets/images/cubeongui.png)


------------------

Thank you for checking out this project. Feel free to fork this repo and make any changes. If you have any questions, contact me at my email
* gdr943@gmail.com
         



