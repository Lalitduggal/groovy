def create_sns_topic_and_add_subscribers(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {
	sh "/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-sns-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-common/01-create-mft-sns-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}"
	sh "/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-sns-cf-stack"
}


def create_elastic_ip(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {
/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-proxy-edge-server-nlb-eip-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-proxy-edge-server/03-create-mft-proxy-edge-server-nlb-eip-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}
/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-proxy-edge-server-nlb-eip-cf-stack

}


def create_ami(COMPONENT_NAME) {
	case COMPONENT_NAME:
		mft-app-server:
			packer build create-mft-app-server-unencrypted-ami-packer-template.json

		mft-proxy-edge-server:
			packer build create-mft-proxy-edge-server-unencrypted-ami-packer-template.json

		mft-mq-gw-server:
			packer build create-mft-mq-gw-server-unencrypted-ami-packer-template.json

}


def encrypt_ami(AMI_NAME) {
  echo "AMI is encrypted"
}


def share_ami(AMI_NAME) {
  echo "AMI in DEV is shared with NONPROD account"
  echo "AMI in DEV is shared with PROD account"
}


def create_rds_ssm_parameter(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {
/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-rds-ssm-parameter-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-app-server-and-mft-rds/04-create-mft-rds-ssm-parameter-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}
/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-rds-ssm-parameter-cf-stack

}


def create_rds(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {

usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-rds-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-app-server-and-mft-rds/05-create-or-restore-mft-rds-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}
usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-rds-cf-stack
	
}


def create_efs(COMPONENT_NAME) {

	case ${COMPONENT_NAME}:
  		mft-app-server:
			/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-app-server-efs-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-app-server-and-mft-rds/02-create-mft-app-server-efs-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}
			/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-app-server-efs-cf-stack
  
  ;;
  mft-mq-gw-server:

  ;;
}


def create_clb(COMPONENT_NAME) {
//mft-app-server
//mft-mq-gw-server
/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-app-server-clb-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-app-server-and-mft-rds/03-create-mft-app-server-clb-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}
/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-app-server-clb-cf-stack

}




def create_sg_for_lc(COMPONENT_NAME) {
//mft-app-server
//mft-proxy-edge-server
//mft-mq-gw-server
/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-app-server-sg-for-lc-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-app-server-and-mft-rds/06-create-mft-app-server-sg-for-lc-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}
/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-app-server-sg-for-lc-cf-stack

}


def update_sg_lc_and_efs(COMPONENT_NAME) {
//mft-app-server
//mft-proxy-edge-server
//mft-mq-gw-server
/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-app-server-sg-with-efs-sg-and-vice-versa-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-app-server-and-mft-rds/07-update-mft-app-server-sg-with-efs-sg-and-vice-versa-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}
/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-app-server-sg-with-efs-sg-and-vice-versa-cf-stack

}


def create_lc_and_asg(COMPONENT_NAME) {
//mft-app-server
//mft-proxy-edge-server
//mft-mq-gw-server
/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-app-server-asg-lc-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-app-server-and-mft-rds/09-create-mft-app-server-asg-lc-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}
/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-app-server-asg-lc-cf-stack

}



def create_nlb_ip(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {
/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-proxy-edge-server-nlb-eip-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-proxy-edge-server/03-create-mft-proxy-edge-server-nlb-eip-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}
/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-proxy-edge-server-nlb-eip-cf-stack

}

def create_nlb_s3_bucket_and_policy(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {
/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-proxy-edge-server-nlb-s3-bucket-and-policy-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-proxy-edge-server/02-create-mft-proxy-edge-server-nlb-s3-bucket-and-policy-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}
/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-proxy-edge-server-nlb-s3-bucket-and-policy-cf-stack

}



def create_nlb(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {
/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-proxy-edge-server-nlb-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-proxy-edge-server/04-create-mft-proxy-edge-server-nlb-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}
/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-proxy-edge-server-nlb-cf-stack

}



def create_clb(COMPONENT_NAME) {
//mft-app-server
//mft-mq-gw-server
/usr/local/bin/aws cloudformation create-stack --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-app-server-clb-cf-stack --template-url https://sandpit-lalit-cf-templates.s3-ap-southeast-2.amazonaws.com/mft-app-server-and-mft-rds/03-create-mft-app-server-clb-cf-stack.yml --parameters ParameterKey=EnvironmentNameP,ParameterValue=${ENVIRONMENT_NAME} ParameterKey=MftStackTypeP,ParameterValue=${ENVIRONMENT_TYPE}
/usr/local/bin/aws cloudformation wait stack-create-complete --stack-name ${ENVIRONMENT_NAME}-${ENVIRONMENT_TYPE}-mft-app-server-clb-cf-stack

}


def attach_elastic_ip_to_nat_gw(ENVIRONMENT_NAME,ENVIRONMENT_TYPE) {

}
