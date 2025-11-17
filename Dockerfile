FROM public.ecr.aws/amazonlinux/amazonlinux:2
EXPOSE 8080
ADD target/elm-d-razorpayment-market.jar elm-d-razorpayment-market.jar 
ENTRYPOINT ["java","-jar","/elm-d-razorpayment-market.jar"]
