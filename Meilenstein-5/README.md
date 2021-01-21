# Laboratory submission - Task Sheet 5
<p>
    <img alt="Module: 3DCG" src="https://img.shields.io/badge/Module-3DCG-blueviolet?style=for-the-badge" />
    <img alt="Lecturer: Prof. Dr.-Ing. Knut Hartmann" src="https://img.shields.io/badge/Lecturer-Prof. Dr.--Ing. Knut Hartmann-blue?style=for-the-badge&link=http://gitlab.inf.fh-flensburg.de/hartmannk" /><br/>
    <img alt="Made with Processing" src="https://img.shields.io/badge/Made%20with-Processing 3.5.4 Library-darkblue?style=for-the-badge&link=https://processing.org/" target="_blank" />
    <img alt="Made with IntelliJ IDEA" src="https://img.shields.io/badge/Made%20with-IntelliJ IDEA Ultimate 2020.3-darkviolet?style=for-the-badge&link=https://www.jetbrains.com/opensource/idea/" target="_blank" />
</p>





> This folder contains the Processing Sketch and other documents for Task Sheet 5 in the 3D Computer Graphics Lab.

## Task

[PDF document](Meilenstein-5/Arbeitsblatt5.pdf) for task sheet 5.

**Summary of the task to be processed for the Processing-Sketch**

> The goal of the worksheet is for you to learn how to construct complex solids from basic geometric primitives using transformations.
>
> **Task 1: Building a 3D scene**
> Create a scene from several instances of the basic primitives cone and cylinder. Use the class you created for 3D coordinate systems (sheet 4, task 2) to show both the world coordinate system and the local coordinate systems of the individual basic bodies.
>
> **Task 2: Animations**
> Let the 3D scene explode after any keyboard input. The explosion centre should be at the origin of the world coordinate system. It is sufficient if the objects move as a whole. Only those who have extreme fun with the task will also disassemble the objects themselves into their components. Another keyboard entry returns the 3D scene to its original state.
>
> **Task 3: Camera perspectives**
> Implements a camera with which the scene can be viewed. This should be able to be moved freely in space. This means that the orientation (rotation around the x, y and z axis) can be changed via mouse movements. In addition, the camera should be able to be moved up and down (parallel to the up vector) and left and right (parallel to the right vector) using the arrow keys. The camera should be able to be moved parallel to the viewing direction using the + and - keys.
>
> In addition, four fixed views (parallel to x, y, z as well as isometric) can be selected via the keyboard. In the fixed views, the camera always looks at the origin of the world coordinate system and is always at the same distance from it.
>
> Please use parallel projections only.



## Realization

- The implementation occured with the [Processing](https://processing.org/) 3.5.4 library (included).
- IntelliJ IDEA Ultimate 2020.3 development environment has been used.



## Usage (Key/Mouse map)

- Mouse
  - `Mouse drag` : rotate the coordinate system
- Keyboard
  - `Arrow UP` : Translate up gen the camera up-axis
  - `Arrow UP` : Translate down the camera up-axis
  - `Arrow LEFT` : Translate camera left
  - `Arrow RIGHT` : Translate camera right
  - `+/-` : Translate the camera on the z-axis
  - Camera modes
    - `1` : Perspective mode
    - `2` : Orthographic mode
    - `3` : X-parallel mode
    - `4` : Y-parallel mode
    - `5` : Z-parallel mode
    - `6` : Isometric mode
  - Coordinate systems
    - `c` : Toggle world coordinate system [*default: **off***]
    - `m` : Toggle model coordinate system [*default: **off***]
  - Animation control
    - `e` : Toggle explosion [*default: **off***]
    - `p` : Toggle music/animation [*default: **off***]



## Demo

### Perspective projection

![image-20210122003738557](Meilenstein-5/README.assets/image-20210122003738557.png)

![image-20210122003817437](Meilenstein-5/README.assets/image-20210122003817437.png)

![image-20210122003903151](Meilenstein-5/README.assets/image-20210122003903151.png)

![image-20210122003949350](Meilenstein-5/README.assets/image-20210122003949350.png)

### Orthographic camera

![image-20210122004030884](Meilenstein-5/README.assets/image-20210122004030884.png)

![image-20210122004057044](Meilenstein-5/README.assets/image-20210122004057044.png)

### X-parallel

![image-20210122004207435](Meilenstein-5/README.assets/image-20210122004207435.png)

![image-20210122004122381](Meilenstein-5/README.assets/image-20210122004122381.png)

### Y-parallel

![image-20210122004237665](Meilenstein-5/README.assets/image-20210122004237665.png)

![image-20210122004140363](Meilenstein-5/README.assets/image-20210122004140363.png)

### Z-parallel

![image-20210122004300616](Meilenstein-5/README.assets/image-20210122004300616.png)

![image-20210122004409853](Meilenstein-5/README.assets/image-20210122004409853.png)



### Isometric

![image-20210122004322624](Meilenstein-5/README.assets/image-20210122004322624.png)

![image-20210122004355203](Meilenstein-5/README.assets/image-20210122004355203.png)


## Contact

**Philipp Borucki (philipp.borucki@stud.hs-flensburg.de)**



## License

Copyright 2021 Philipp Borucki.<br/>
This project is licensed under a proprietary license and is limited to the restrictions of intellectual property rights for academic writing and submission of laboratory work.

The use for demonstration purposes, especially in teaching and research, is expressly desired and permitted.




***