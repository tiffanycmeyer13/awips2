#!/bin/bash

dir="$( cd "$(dirname "$0")" ; pwd -P )"
pushd $dir
. ../../buildEnvironment.sh

#Build initial image with init enabled from a rocky8 init image
podman build --rm -t local/r8-systemd -f Dockerfile.rocky8-systemd.el8
##### Build next image
# Updates default packages
# Enables powertools
# Removes versions of Java 8
# Enables postgres v12
# Installs dependencies
# Download the awips_install.sh script
podman build --rm -t local/r8-init -f Dockerfile.rocky8-init.el8

#### #Run/start latest image as a container
# Run image
# Install EDEX
# Disable purging
# Add awips as sudoer
podman run -di -v /sys/fs/cgroup:/sys/fs/cgroup:ro -p 9581:9581 -p 9582:9582 --name edex2 local/r8-init
podman exec edex2 ./awips_install.sh --edex
podman exec edex2 su - awips -c "sed -i 's/true/false/' /awips2/edex/conf/resources/purge.properties"
podman exec edex2 sed -i 's/# %wheel/%wheel/' /etc/sudoers
podman exec edex2 usermod -aG wheel awips
podman exec edex2 su - awips -c "mkdir -p /awips2/archive/data"

### 
#podman commit 


