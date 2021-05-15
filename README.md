# gPicHost

[![Build](https://github.com/genghis-yang/gpichost/actions/workflows/build.yml/badge.svg)](https://github.com/genghis-yang/gpichost/actions/workflows/build.yml)

This is a picture hosting app using github as the backend.


## Build Native Image

What's a native image? See [GraalVM Native Image](https://www.graalvm.org/reference-manual/native-image/).

- If you have installed GraalVM and native-image tool, you can just run 
    
  ```shell
  $ sbt ni
  ```
  
  It will be running for a long while(5~10 minutes), then generate a native image in `./target/graalvm-native-image/`.

- If you haven't installed GraalVM and native-image tool, and don't want to install them.
  You can try to build native image with docker-compose:
  
  ```shell
  $ docker-compose run build-native-image
  ```

**FYI**: Image building requires large amounts of memory, especially if you build large images with truffle included.
See [issue for insufficient memory](https://github.com/oracle/graal/issues/1184). 
It should be at least 4G heap space. I set `-Xmx=6144m` for `graalVMNativeImageOptions`.
According to [GitHub Actions Documentation](https://docs.github.com/en/actions/using-github-hosted-runners/about-github-hosted-runners#supported-runners-and-hardware-resources),
a Linux runner has 7 GB of RAM memory. It satisfies GraalVM requirement.

