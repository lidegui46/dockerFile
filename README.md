# dockerFile
local code -> git -> linux -> docker -> run

# local code 注意事项
1、application.yml 
   
   port：是指项目在容器中运行的端口，外部访问时，需要通过 docker 
   run 时把对外访问的接口与 docker 内运行的接口进行绑定，外部才能
   访问 docker 内运行的项目；

2、pom.xml

    
   