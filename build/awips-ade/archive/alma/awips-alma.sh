#!/bin/bash
dir="$( cd "$(dirname "$0")" ; pwd -P )"
pushd $dir 
. ../../../buildEnvironment.sh
#img="awips-edex-23.4.1-1"
img="alma-init"

if [ -z "$1" ]; then
  echo "supply type (el8)"
  exit
fi
os_version=$1

existing=$(podman images |grep ${img} | grep $1 | awk '{ print $3 }')
if [ ! -z "$existing" ]; then
   podman rmi $existing
fi
pushd /awips2/repo/awips2-builds/build/awips-ade/archive/alma
podman build -t tiffanym13/${img} -f Dockerfile.${img}.${os_version} .
podmanID=$(podman images | grep ${img} | grep latest | awk '{print $3}' | head -1 )
podman tag $podmanID tiffanym13/${img}:${os_version} 
podman rmi tiffanym13/${img}:latest
podman push tiffanym13/${img}:${os_version}
