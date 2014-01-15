JAHMM
=====

`Jahmm` is a Java library implementing the various, well-known algorithms
related to Hidden Makov Models (HMMs for short).

The source code of the library is available; it is licensed under GPL
(see the resource/COPYING file).

This library is short and simple.  It's been written for clarity.  It
is particularly well suited for research and academic use.

The website associated to this library is: [http://jahmm.googlecode.com/]
Most information related to this software can be found there.

This repository is a fork of the original jahmm library that can be found here: [http://jahmm.googlecode.com/]

Compiling
---------

To compile the library, you simply need to compile all the files held
in the `jahmm/src` directory.  Thus, simply calling `javac` with all the
.java files held in the `jahmm/src` directory as arguments compiles everything.

Jahmm requires `Java 1.5.0`.

Running
-------

To use it, simply launch:
```
javac -classpath /path/to/jahmm-<version>.jar Myprogram.java
```
to compile your program, and:
```
java -cp /path/to/jahmm-<version>.jar myMainClass
```
(e.g. `java -cp /home/smith/java_class/jahmm-0.6.2.jar test/Testing`)

...to run it.
You can also put the `.jar` file in your classpath.



Testing
-------

Regression (JUnit) tests have also been written ; see the `jahm/test` directory.


Files
-----

- pom.xml: the 'maven' project file.
- build.xml: the 'ant' build file.
- `src/`:       all the .java files.
  `src/.../distributions`: Pseudo random distributions.
  `src/.../jahmm`: The jahmm library itself.  This directory holds one
             directory per java package; see the jahmm website for
             more information about each of them.
- `test/`: Regression tests.
- `examples`: various example files
- `README.md`: this file.
- `CHANGES`: changelog.
- `LICENSE`: license file.
- `THANKS`: contributors.

Jutils
------

The program uses a java library called `jutils` that can be found here: <https://github.com/KommuSoft/jutil>


Contact
-------

`Jahmm's` original author is Jean-Marc Francois.  
Feel free to send comments and questions related to this library at:
- http://code.google.com/p/jahmm/issues/list (if you have an issue with the library)
- http://groups.google.com/group/jahmm-discuss or jahmm-discuss@googlegroups.com
  (for questions/comments)
  
The author of this repository is Willem Van Onsem <Willem.VanOnsem@cs.kuleuven.be>
this version aims to improve speed and enables the use of more sophisticated hidden markov
models like the Input-Output Hidden Markov Model (IOHMM)
