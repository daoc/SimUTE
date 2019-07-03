## Ya está todo incluido, pero...

### Info general: [https://gouessej.wordpress.com/2012/08/01/java-3d-est-de-retour-java-3d-is-back/]

### Para incluir las dependencias es necesario

descargar los .jar de simbad y de jogamp java3d:

[https://sourceforge.net/projects/simbad/files/latest/download]

[http://jogamp.com/deployment/java3d/1.6.0-final/jogamp-java3d.7z]

Descomprimir todos los archivos y poner los jar en algún subdirectorio,
por ejemplo C:\MiJarDir

Crear un repositorio Maven local al proyecto: en la raíz del proyecto crear una carpeta
por ejemplo "lib", ir a la raíz del proyecto, abrir ahí una consola y ejecutar
uno por uno los siguientes comandos:

```
mvn deploy:deploy-file -DgroupId=simbad -DartifactId=simbad -Dversion=1.4 -Durl=file:./lib/ -DrepositoryId=lib -DupdateReleaseInfo=true -Dfile=C:\\MiJarDir\\simbad-1.4.jar

mvn deploy:deploy-file -DgroupId=jogamp.j3d -DartifactId=j3dcore -Dversion=1.6 -Durl=file:./lib/ -DrepositoryId=lib -DupdateReleaseInfo=true -Dfile=C:\\MiJarDir\\j3dcore.jar

mvn deploy:deploy-file -DgroupId=jogamp.j3d -DartifactId=j3dutils -Dversion=1.6 -Durl=file:./lib/ -DrepositoryId=lib -DupdateReleaseInfo=true -Dfile=C:\\MiJarDir\\j3dutils.jar

mvn deploy:deploy-file -DgroupId=jogamp.j3d -DartifactId=vecmath -Dversion=1.6 -Durl=file:./lib/ -DrepositoryId=lib -DupdateReleaseInfo=true -Dfile=C:\\MiJarDir\\vecmath.jar
```

Luego incluir en el POM las dependencias:

```
<dependency>
    <groupId>simbad</groupId>
    <artifactId>simbad</artifactId>
    <version>1.4</version>
</dependency>
<dependency>
    <groupId>jogamp.j3d</groupId>
    <artifactId>j3dcore</artifactId>
    <version>1.6</version>
</dependency>    
<dependency>
    <groupId>jogamp.j3d</groupId>
    <artifactId>j3dutils</artifactId>
    <version>1.6</version>
</dependency>
<dependency>
    <groupId>org.jogamp.gluegen</groupId>
    <artifactId>gluegen-rt-main</artifactId>
    <version>2.3.2</version>
</dependency>
<dependency>
    <groupId>org.jogamp.jogl</groupId>
    <artifactId>jogl-all-main</artifactId>
    <version>2.3.2</version>
</dependency>
```

