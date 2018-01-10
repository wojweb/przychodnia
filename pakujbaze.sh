#!/bin/bash
#robienie backupa dla Szymona

mysqldump -u pan -p --databases przychodnia -R > backup.mysql

