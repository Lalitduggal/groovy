def create_sns_topic_and_add_subscribers(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {
	sh "/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-sns-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-common/01-create-mft-sns-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}"
	sh "/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-sns-cf-stack"
}


def create_elastic_ip(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {

}


def attach_elastic_ip_to_nat_gw(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {

}

def create_ami('COMPONENT_NAME') {
//mft-app-server
packer build 
//mft-proxy-edge-server
//mft-mq-gw-server
}


def encrypt_ami('AMI_NAME') {
  echo "AMI is encrypted"
}


def share_ami('AMI_NAME') {
  echo "AMI in DEV is shared with NONPROD account"
  echo "AMI in DEV is shared with PROD account"
}


def create_rds(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {

}


def create_efs('COMPONENT_NAME') {

case COMPONENT_NAME:
  mft-app-server:
  
  ;;
  mft-mq-gw-server:

  ;;
}


def create_clb('mft-app-server') {
//mft-app-server
//mft-mq-gw-server

}


def create_rds_ssm_parameter(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {

}


def create_sg_for_lc('mft-app-server') {
//mft-app-server
//mft-proxy-edge-server
//mft-mq-gw-server

}


def update_sg_lc_and_efs('mft-app-server') {
//mft-app-server
//mft-proxy-edge-server
//mft-mq-gw-server

}


def create_lc_and_asg('mft-app-server') {
//mft-app-server
//mft-proxy-edge-server
//mft-mq-gw-server

}


def create_nlb_s3_bucket_and_policy(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {

}


def create_nlb_ip(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {

}


def create_nlb(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {

}



def create_clb('mft-mq-gw-server') {
//mft-app-server
//mft-mq-gw-server
}
