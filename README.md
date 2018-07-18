# backend
在libsvm目录下先运行:  
mvn install:install-file -Dfile=libsvm.jar -DgroupId=com.svm -DartifactId=svm -Dversion=1.0 -Dpackaging=jar  
否则maven依赖出错，无法使用libsvm
