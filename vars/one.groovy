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

def create_sns_topic(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {
	sh '/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-sns-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-common/01-create-mft-sns-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}'
	sh '/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-sns-cf-stack'
}
