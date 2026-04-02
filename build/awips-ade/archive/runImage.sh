podman pull docker.io/tiffanym13/edex:data
podman run -d --name edex-data -p 9581:9581 -p 9582:9582 tiffanym13/edex:data
podman exec -ti edex-data /bin/bash
#Wait for EDEX to start

su - awips
disable purging

#Option 1 - Works
rsync -aP /home/awips/data/TLX/TLX_N0X_20250811_1802 /awips2/edex/data/manual/

#Option 2 - Works
/awips2/python/bin/python /awips2/fxa/bin/src/qpidNotify/qpidNotify.py /home/awips/data/TLX/TLX_N0B_20250811_1802
