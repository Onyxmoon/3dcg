# Laboratory submission - Task Sheet 3
<p>
    <img alt="Module: 3DCG" src="https://img.shields.io/badge/Module-3DCG-blueviolet?style=for-the-badge" />
    <img alt="Lecturer: Prof. Dr.-Ing. Knut Hartmann" src="https://img.shields.io/badge/Lecturer-Prof. Dr.--Ing. Knut Hartmann-blue?style=for-the-badge&link=http://gitlab.inf.fh-flensburg.de/hartmannk" /><br/>
    <img alt="Made with Processing" src="https://img.shields.io/badge/Made%20with-Processing 3.5.4 Library-darkblue?style=for-the-badge&link=https://processing.org/" target="_blank" />
    <img alt="Made with IntelliJ IDEA" src="https://img.shields.io/badge/Made%20with-IntelliJ IDEA Ultimate 2020.2-darkviolet?style=for-the-badge&link=https://www.jetbrains.com/opensource/idea/" target="_blank" />
</p>




> This folder contains the Processing Sketch and other documents for Task Sheet 3 in the 3D Computer Graphics Lab.

## Task

[PDF document](Meilenstein-3/Arbeitsblatt3.pdf) for task sheet 3.

**Summary of the task to be processed for the Processing-Sketch**

> Learning objectives of the third task are the application of operations on and with two-dimensional vectors, the visual verification of the solution in two-dimensional coordinate systems and the conversion of vector operations into processing. All these skills are especially important for the exam.
> 
>**Task 1**
> Create a class that draws the lines of a 2D model of your choice. The shape is to be stored in a class method **makeShape()** n a vertex array, which is then drawn in the method **renderShape()** using the functions **beginShape()** and **endShape()**. In addition the `position`, `rotation` and `scaling` should be adjustable via the class properties position, orientation and size. These properties are to be applied in the method **render()** by transformations in such a way that the model is drawn on the drawing surface at the desired position with the appropriate rotation and scaling.
> 
> Note that only the lines and no filling should be drawn. The lines should also have the same line thickness for each model size.
>
> **Task 2**
> Several of your models should be drawn in a circle around the center of the drawing area. The models are to be drawn using the class created in task 1. The radius (distance to the center of the drawing surface), the angular distance between the models, and the size and orientation of the models should be able to be increased and decreased by keyboard input.
>
> **Task 3**
> Task 3 contains exercises of mathematical operations. See [Solutions](Meilenstein-3/Borucki_Philipp_Aufgabenblatt3_Aufgabe3.md).



## Realization

- [PDF document](Meilenstein-3/Borucki_Philipp_3DCG_Aufgabenblatt3.pdf) with solution for the task sheet
- [Markdown document](Meilenstein-3/Borucki_Philipp_Aufgabenblatt3_Aufgabe3.md) with the solution for task 3 of the task sheet
- The implementation occured with the [Processing](https://processing.org/) 3.5.4 library (included).
- IntelliJ IDEA Ultimate 2020.2 development environment has been used.



## Usage (Key/Mouse map)

- Keyboard
  - ```PAGE UP/DOWN``` : Increase/Decrease stars count
  - ```+ / -``` : Increase/Decrease stars size
  - ```ARROW UP/DOWN ```  : Scale radius up/down
  - ```ARROW LEFT/RIGHT```  : Rotate stars left/right 



## Demo

![](Meilenstein-3/README.assets/image-20201208030442891.png)

![](Meilenstein-3/README.assets/image-20201208030553003.png)

## Contact

**Philipp Borucki (philipp.borucki@stud.hs-flensburg.de)**



## License

Copyright 2020 Philipp Borucki.<br/>
This project is licensed under a proprietary license and is limited to the restrictions of intellectual property rights for academic writing and submission of laboratory work.




***