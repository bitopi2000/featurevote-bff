FROM public.ecr.aws/lambda/java:17

COPY target/featurevote-bff-0.0.1-SNAPSHOT.jar ${LAMBDA_TASK_ROOT}

CMD [ "featurevote-bff-0.0.1-SNAPSHOT.jar" ]