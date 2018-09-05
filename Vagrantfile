private_key_path = File.join(Dir.home, ".ssh", "id_rsa")
public_key_path = File.join(Dir.home, ".ssh", "id_rsa.pub")
insecure_key_path = File.join(Dir.home, ".vagrant.d", "insecure_private_key")
private_key = IO.read(private_key_path)
public_key = IO.read(public_key_path)
Vagrant.configure(2) do |config|
	config.vm.define "app" do |app_config|
		app_config.vm.box = "minimal/centos6"
		app_config.ssh.insert_key = false
		app_config.ssh.private_key_path = [ private_key_path, insecure_key_path # to provision the first time 
		]
		app_config.vm.provision :shell, :inline => <<-SCRIPT
		set -e
		echo '#{private_key}' > /home/vagrant/.ssh/id_rsa
		chmod 600 /home/vagrant/.ssh/id_rsa
		echo '#{public_key}' > /home/vagrant/.ssh/authorized_keys
		chmod 600 /home/vagrant/.ssh/authorized_keys
	SCRIPT
		app_config.vm.network "private_network", :ip => '10.0.0.2'
		app_config.vm.network "forwarded_port", guest: 22, host: 1111, id: "ssh"
		app_config.vm.network "forwarded_port", guest: 80, host: 8080
		app_config.vm.provision :shell, :inline => "sudo rm /etc/localtime && sudo ln -s /usr/share/zoneinfo/Europe/Warsaw /etc/localtime", run: "always"
	end
	config.vm.define "monitor" do |monitor_config|
		monitor_config.vm.box = "minimal/centos6"
		monitor_config.ssh.insert_key = false
		monitor_config.ssh.private_key_path = [ private_key_path, insecure_key_path # to provision the first time
		]
		monitor_config.vm.provision :shell, :inline => <<-SCRIPT
		set -e
		echo '#{private_key}' > /home/vagrant/.ssh/id_rsa
		chmod 600 /home/vagrant/.ssh/id_rsa
		echo '#{public_key}' > /home/vagrant/.ssh/authorized_keys
		chmod 600 /home/vagrant/.ssh/authorized_keys
	SCRIPT
		monitor_config.vm.network "private_network", :ip => '10.0.0.3'
		monitor_config.vm.network "forwarded_port", guest: 22, host: 2222, id: "ssh"
		monitor_config.vm.network "forwarded_port", guest: 80, host: 8181
		monitor_config.vm.network "forwarded_port", guest: 443, host: 3333
		monitor_config.vm.provision :shell, :inline => "sudo rm /etc/localtime && sudo ln -s /usr/share/zoneinfo/Europe/Warsaw /etc/localtime", run: "always"
	end
end