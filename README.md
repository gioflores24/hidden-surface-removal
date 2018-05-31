# Hidden Surface Removal 

 This program reads a .txt file, and fills a polygon (in the case of this project, a cube)
 based on the vertices and coordinates given in the following format:



* 9 (number of vertices + 1 for the homogeneous coordinates)
* vertices //(x,y,z)
  * 0.5, -0.5,  0.5 (vertex 1)
  * 0.5,  0.5,  0.5 
  * -0.5,  0.5,  0.5   
  * -0.5, -0.5,  0.5   
  *  0.5, -0.5, -0.5   
  * -0.5, -0.5, -0.5   
  * -0.5,  0.5, -0.5   
  *  0.5,  0.5, -0.5   
* edges
  * 0, 1
  * 1, 2
  * 2, 3
  * 3, 0
  * 4, 5
  * 5, 6
  * 6, 7
  * 7, 4
  * 0, 4
  * 1, 7
  * 2, 6
  * 3, 5
* faces
  * 0, 1, 2, 3
  * 4, 5, 6, 7
  * 0, 4, 7, 1
  * 3, 2, 6, 5
  * 5, 4, 0, 3
  * 1, 7, 6, 2


An example is provided in this repository. 

---------------------------

After creating the .txt file, you need to load it by pressing "Read File" in the GUI after running the main program: 

Image displayed here:

* (https://github.com/gioflores24/hidden-surface-removal/tree/master/assets/images/gui.png)


