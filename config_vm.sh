#!/bin/bash
sed -i 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/selinux/config
sed -i 's/ONBOOT=no/ONBOOT=yes/g' /etc/sysconfig/network-scripts/ifcfg-eth0
sed -i 's/^\(Defaults.*requiretty\)/#\1/' /etc/sudoers
chkconfig iptables off
rm -f /etc/udev/rules.d/70-persistent-net.rules
mkdir -m 0700 -p /home/vagrant/.ssh
mv /root/id_rsa.pub /home/vagrant/.ssh/authorized_keys
chmod 600 /home/vagrant/.ssh/authorized_keys
chown -R vagrant:vagrant /home/vagrant/.ssh
echo "vagrant ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers