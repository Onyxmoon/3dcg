# 3DCG — Aufgabenblatt 3

## Aufgabe 3

Es ist ein Dreieck mit folgenden Eckpunkten gegeben:
p1 = (2, 3); p2 = (-4, -1); p3 = (3, -3)

![Aufgabenblatt3Aufgabe3Graph](Meilenstein-3/Borucki_Philipp_Aufgabenblatt3_Aufgabe3.assets/Aufgabenblatt3Aufgabe3Graph.svg)

### a) Konstruiert folgende Vektoren

#### Formel

$`
\vec{ab} = \vec{b} - \vec{a}
`$

#### Lösung

##### v1To2: Vektor ausgehend vom Punkt p1 zum Punkt p2

$`
\vec{v1To2} = \vec{p2} - \vec{p1} = \left ( \begin{matrix} (-4)-2 \\ (-1)-3 \end{matrix}  \right ) = \left ( \begin{matrix} -6 \\ -4 \end{matrix}  \right )
`$



##### v1To3: Vektor ausgehend vom Punkt p1 zum Punkt p3

$`
\vec{v1To3} = \vec{p3} - \vec{p1} = \left ( \begin{matrix} 3-2 \\ (-3)-3 \end{matrix}  \right ) = \left ( \begin{matrix} 1 \\ -6 \end{matrix}  \right ) 
`$



### b) Bestimmt die Länge der Vektoren v1To2 und v1To3.

#### Formel

$`
\left|\vec{v}\right|  = \sqrt{\sum\limits_{i = 1}^{n}{(v_{i})^2} } \space\space\space\space\space\space\space\space\space\space\space \space \text{n := dim(}\vec{v}\text{)} \space \space \left\{n \space \forall \in  \mathbb{N} \space|\space n \geq 1  \right\}
`$

#### Lösung

##### v1To2

$`
\left|\vec{v1To2}\right|  = \sqrt{\sum\limits_{i = 1}^{2}{(v1To2_{i})^2} } =  \sqrt{(-6)^2+(-4)^2} = \sqrt{52} \approx 7.211
`$

##### v1To3

$`
\left|\vec{v1To3}\right|  = \sqrt{\sum\limits_{i = 1}^{2}{(v1To3_{i})^2} } =  \sqrt{(1)^2+(-6)^2} = \sqrt{37} \approx 6.083  
`$



### c) Bestimmt den Abstand der Punkte p2 und p3.

#### Formel

$`
\vec{ab} = \vec{b} - \vec{a}
`$

$`
\left|\vec{v}\right|  = \sqrt{\sum\limits_{i = 1}^{n}{(v_{i})^2} } \space\space\space\space\space\space\space\space\space\space\space \space \text{n := dim(}\vec{v}\text{)} \space \space \left\{n \space \forall \in  \mathbb{N} \space|\space n \geq 1  \right\}
`$

#### Lösung

##### Vektor zwischen p2 und p3 konstruieren

$`
\vec{v2To3} = \vec{p3} - \vec{p2} = \left ( \begin{matrix} 3-(-4) \\ (-3)-(-1) \end{matrix}  \right ) = \left ( \begin{matrix} -7 \\ -2 \end{matrix}  \right )
`$

##### Länge bestimmen

$`
\left|\vec{v2To3}\right|  = \sqrt{\sum\limits_{i = 1}^{2}{(v2To3_{i})^2} } =  \sqrt{(7)^2+(-2)^2} = \sqrt{53} \approx 7.280
`$

##### Abstand

$`
\text{Abstand} = \left|\vec{v2To3}\right| = \sqrt{53} \approx 7.280
`$



### d) Skaliert den Vektor v1To2 mit den Faktoren -2 und 3.

#### Formel

$`
\vec{v_s}  = \vec{v} \cdot s \space\space\space\space\space\space\space\space\space\space\space \space \left\{s \in  \mathbb{R} \space \wedge \space \vec{v} \in \mathbb{R}^n \space |\space n \in \mathbb{N} \right\}
`$

#### Lösung

##### v1To2 skaliert mit -2

$`
\vec{v1To2} \space \cdot -2 = \left ( \begin{matrix} (-6)\cdot(-2) \\ (-4)\cdot(-2) \end{matrix}  \right ) = \left ( \begin{matrix} 12 \\ 8 \end{matrix}  \right )
`$

##### v1To2 skaliert mit 3

$`
\vec{v1To2} \space \cdot 3 = \left ( \begin{matrix} (-6)\cdot3 \\ (-4)\cdot3 \end{matrix}  \right ) = \left ( \begin{matrix} -18 \\ -12 \end{matrix}  \right )
`$



### e) Normalisiert die Vektoren v1To2 und v1To3

#### Formel

$`
\frac{\vec{v}}{\lvert \vec{v} \rvert} \space\space\space\space\space\space\space\space\space\space\space\space \left\{ \space \vec{v} \in \mathbb{R}^n \space |\space n \in \mathbb{N} \right\}
`$

#### Lösung

##### v1To2 normalisieren

$`
\vec{nv1To2} = \frac{\vec{v1To2}}{\lvert \vec{v1To2} \rvert} =  \left ( \begin{matrix} \frac{-6}{\sqrt{52} }  \\ \frac{-4}{\sqrt{52} }  \end{matrix}  \right ) \approx \left ( \begin{matrix} -0.832 \\ -0.555 \end{matrix}  \right )
`$

#####  v1To3 normalisieren

$`
\vec{nv1To3} = \frac{\vec{v1To3}}{\lvert \vec{v1To3} \rvert} =  \left ( \begin{matrix} \frac{1}{\sqrt{37} }  \\ \frac{-6}{\sqrt{37} }  \end{matrix}  \right ) \approx \left ( \begin{matrix} -0.164 \\ -0.986 \end{matrix}  \right )
`$



### f) Bestimmt den Winkel zwischen den Vektoren v1To2 und v1To3

#### Formel

$`
\alpha = \cos^{-1}\left(\frac{\vec{v}\cdot\vec{w}}{\left|\vec{v}\right|\cdot\left|\vec{w}\right|  } \right)
`$

#### Lösung

$`
\alpha = \cos^{-1}\left(\frac{\vec{v1To2}\cdot\vec{v1To3}}{\left|\vec{v1To2}\right|\cdot\left|\vec{v1To3}\right|  } \right) = \cos^{-1}\left(\frac{(-6\cdot1) + (-4\cdot-6)}{\sqrt{52} \space \cdot \space \sqrt{37}  } \right) \approx \cos^{-1}(0,410) \approx 1,148
`$



### g) Bestimmt die Normale der von den Vektoren v1To2 und v1To3 aufgespannten Ebene.

#### Formel

$`
\vec{vx} = \vec{v} \times \vec{w} \space\space\space\space\space\space\space\space\space\space\space\space \left\{ \space \vec{v} \in \mathbb{R}^n \space |\space n \in \mathbb{N} \space \wedge n \geq 3\space \right\}
`$

#### Lösung

$`
\vec{v1To2} \times \vec{v1To3} = \left ( \begin{matrix} -6 \\ -4 \\ 0 \end{matrix}  \right ) \times \left ( \begin{matrix} 1 \\ -6 \\ 0 \end{matrix}  \right ) = \left ( \begin{matrix} (-4)\cdot0-0\cdot(-6) \\ 0\cdot1-(-6)\cdot 0 \\ (-6)\cdot-(-6)-(-4)\cdot 1 \end{matrix}  \right ) 
= \left ( \begin{matrix} 0-0 \\ 0-0 \\ 36-(-4) \end{matrix}  \right ) = \left ( \begin{matrix} 0 \\ 0 \\ 40 \end{matrix}  \right ) 
`$



### h) Bestimmt den Schwerpunkt des von den Punkten p1, p2 und p3 gebildeten Dreiecks.

#### Formel

$`
\bar{p} = \frac{1}{n} \cdot \sum\limits_{i = 1}^{n}{p^i} \space\space\space\space\space\space\space\space\space\space\space \space \text{n := count(}P\text{)} \space \space P := \left\{\space p \in  \mathbb{R}^d \space|\space d \geq 1  \right\}
`$

#### Lösung

$`
\bar{p} = \frac{1}{3} \cdot \left ( \begin{matrix} 2+(-4)+3 \\ 3+(-1)+(-3) \end{matrix}  \right ) = \left ( \begin{matrix} \frac{1}{3}  \\ -\frac{1}{3}  \end{matrix}  \right )
`$



### i) Ein Objekt befindet sich aktuell in p1 und bewegt sich jede Sekunde 2 Einheiten in Richtung des Vektors v1To2. Bestimmt die Position des Objektes vor einer Sekunde und in drei Sekunden.

#### Formel

$`
\text{Angepasste parametrische Geradengleichung}
`$


$`
\vec{ps} = \vec{u} + \vec{v} \cdot (s \cdot 2) \space\space\space\space\space\space\space\space\space \text{wobei: } \vec{u} := \text{Ursprung} \space\wedge \vec{v} := \text{Richtung} \space\wedge s := \text{Sekunden}
`$

#### Lösung

##### -1 Sekunde

$`
\vec{ps1} = \vec{p1} + \vec{v1To2} \cdot (-1 \cdot 2) = \left ( \begin{matrix} (2+(-6))\cdot -2 \\ (3+(-4))\cdot -2 \end{matrix}  \right ) = \left ( \begin{matrix} 14 \\ 11 \end{matrix}  \right )
`$

##### 3 Sekunden

$`
\vec{ps2} = \vec{p1} + \vec{v1To2} \cdot (3 \cdot 2) = \left ( \begin{matrix} (2+(-6))\cdot 6 \\ (3+(-4))\cdot 6 \end{matrix}  \right ) = \left ( \begin{matrix} 38 \\ 21 \end{matrix}  \right )
`$



### j) Gebt einen Algorithmus an, um zu prüfen, ob zwei Vektoren orthogonal sind, d.h. im Winkel von 90 Grad stehen.

#### Lösung

$`
\vec{v_1}\cdot\vec{v_2}= 0 \space \space \Rightarrow \space \space \vec{v_1} \space \bot \space \vec{v_2}
`$

