Build command

```mvn clean compile assembly:single```

Build and deploy

```mvn clean compile assembly:single && scp target/I2CMaster-1.0-SNAPSHOT-jar-with-dependencies.jar pi@192.168.1.103:/home/pi/Projects```
