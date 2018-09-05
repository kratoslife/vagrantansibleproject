#!/bin/bash
sed -i 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/selinux/config
sed -i 's/^\(Defaults.*requiretty\)/#\1/' /etc/sudoers
rm -f /etc/udev/rules.d/70-persistent-net.rules
echo "vagrant ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers
yum -y install kernel-devel-`uname -r` gcc perl
wget "https://download.virtualbox.org/virtualbox/5.2.16/Oracle_VM_VirtualBox_Extension_Pack-5.2.16-123759.vbox-extpack"
VBoxManage extpack install --replace Oracle_VM_VirtualBox_Extension_Pack-5.2.16-123759.vbox-extpack
/sbin/vboxconfig
wget "https://releases.hashicorp.com/vagrant/2.1.2/vagrant_2.1.2_x86_64.rpm"
yum install -y vagrant_2.1.2_x86_64.rpm
chmod -m 777 /home/vagrant/vagrant-homework
chown -R vagrant:vagrant /home/vagrant/vagrant-homework
cd /home/vagrant/vagrant-homework
cp /home/vagrant/.ssh/* /home/vagrant/.vagrant.d/
cd /home/vagrant/vagrant-homework
su - vagrant