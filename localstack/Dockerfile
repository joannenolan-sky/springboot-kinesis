FROM localstack/localstack:0.12.11

COPY setup/aws/* aws/
RUN mv aws/ ~/.aws

COPY setup/stream-setup stream-setup/

EXPOSE 4566

ENTRYPOINT [ "stream-setup/configurelocalstack.sh"]


