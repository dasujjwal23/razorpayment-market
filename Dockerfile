FROM public.ecr.aws/amazonlinux/amazonlinux:2

# Install Java 17 (Amazon Corretto)
RUN yum update -y && \
    yum install -y java-17-amazon-corretto-headless && \
    yum clean all

# Copy jar
COPY target/elm-d-razorpayment-market.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
