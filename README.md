# EmployeeManagementSystem

This is a CRUD APIs application made using spring boot, mysql, redis.Here there is mapping between employee and their respective department.Supports CRUD operations on employees data.

redis key <emp_id , department_name> 
If any employee id mapping not exists in redis then default department_name will show i.e. default_dept 

Memcache has been used so that if another employee with the same DB name comes via api it should refer the mem cache data source and not create a db data source again.

APIs have validations on strict types and compulsory parameters and returning proper error codes & error messages.

Also deployed the same on AWS lambda and tested APIs on API gateway using AWS RDS for mysql and Elastic-Cache for redis.
