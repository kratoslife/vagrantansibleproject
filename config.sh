#!/bin/bash
usermod -aG wheel vagrant
cd /etc/yum.repos.d
sudo wget http://download.virtualbox.org/virtualbox/rpm/rhel/virtualbox.repo
sudo yum clean all
sudo yum install -y VirtualBox-5.2.x86_64
sudo usermod -aG vboxusers vagrant
cd ~/
mkdir -m 777 /home/vagrant/.vagrant.d
cd /home/vagrant