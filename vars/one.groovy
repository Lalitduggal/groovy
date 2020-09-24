def one(){
  checkout(
    [
      $class: 'GitSCM',
      branches: [
        [
          name: '*/master'
        ]
      ],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      submoduleCfg: [],
      userRemoteConfigs:[
        [
          url: 'https://github.com/Lalitduggal/groovy.git'
        ]
      ]
   ])
}


def two(message){
  echo "The message is: ${message}"
}

def mft_app_server() {
  sh '/usr/local/bin/aws cloudformation list-stacks'
}
