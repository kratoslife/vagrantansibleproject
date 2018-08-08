# -*- mode: ruby -*-
# vi: set ft=ruby :
Vagrant.configure(2) do |config|
  config.vm.define "deploy" do |deploy_config|
    deploy_config.vm.box = "minimal/centos6"
    deploy_config.vm.network "private_network", :ip => '10.10.10.2'
	deploy_config.vm.network "forwarded_port", guest: 22, host: 5555, id: "ssh"
	deploy_config.vm.provision :shell, path: "deploy_ins.sh"
	deploy_config.vm.provision "ansible" do |ansible|
		ansible.playbook = "playbook-deployment.yml"
	end
  end
  config.vm.define "appsrv" do |appsrv_config|
    appsrv_config.vm.box = "minimal/centos6"
    appsrv_config.vm.network "private_network", :ip => '10.10.10.3'
	appsrv_config.vm.network "forwarded_port", guest: 22, host: 3333, id: "ssh"
    appsrv_config.vm.network "forwarded_port", guest: 80, host: 8080
	appsrv_config.vm.provision "ansible" do |ansible|
		ansible.playbook = "playbook-appsrv.yml"
	end
  end
  config.vm.define "monsrv" do |monsrv_config|
    monsrv_config.vm.box = "minimal/centos6"
    monsrv_config.vm.network "private_network", :ip => '10.10.10.4'
	monsrv_config.vm.network "forwarded_port", guest: 22, host: 4444, id: "ssh"
    monsrv_config.vm.network "forwarded_port", guest: 80, host: 8181
    monsrv_config.vm.network "forwarded_port", guest: 443, host: 4433
	monsrv_config.vm.provision "ansible" do |ansible|
		ansible.playbook = "playbook-moonsrv.yml"
	end
   end
   config.vm.define "appsrv" do |appsrv_config|
	appsrv_config.vm.provision "ansible" do |ansible|
		ansible.playbook = "playbook-appsrv2.yml"
	end
  end
end