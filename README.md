## INVENTORY SYSTEM BACKEND

***

### Progress
- [x] CRUD Operations
- [x] JPA Relationships
- [] Login System
- [] Integration testing with [Frontend](https://github.com/Mac-Peralta/inventory-react-ui)

***  

### Setting up
1. Clone this repository
2. Navigate to src/main/resources
3. Rename **application.properties.temp** to **application.properties**
4. Open file in editor of your choice
5. Fill in fields with **** brackets (**BE SURE TO REMOVE BRACKETS AFTER**)
6. Program can now be launched in IDE of choice

***

### Key parameters

#### Employee
* firstName
* lastName
* email
* username
* password
* role

#### Item
* type
* name
* purchaseDate

#### Location
* address
* city
* country

#### Request
* details
* status
* requestDate

***

### Endpoints 

#### Employee
1. See All
    * GET Method
    * localhost:8080/api/employee
2. Add New
    * POST Method
    * localhost:8080/api/employee
    * Requires employee information in request body
3. Update Existing
    * PUT Method
    * localhost:8080/api/employee/**{id}**
        * **{id}** - replace with id of employee to edit
    * Requires employee information in request body
4. Delete Existing
    * DELETE Method
    * localhost:8080/api/employee/**{id}**
        * **{id}** - replace with id of employee to edit

#### Item
1. See All
    * GET Method
    * localhost:8080/api/item
2. Add New
    * POST Method
    * localhost:8080/api/item
    * Requires item information in request body
3. Update Existing
    * PUT Method
    * localhost:8080/api/item/**{id}**
        * **{id}** - replace with id of item to edit
    * Requires item information in request body
4. Delete Existing
    * DELETE Method
    * localhost:8080/api/item/**{id}**
        * **{id}** - replace with id of item to delete
5. Assign Employee
    * POST Method
    * localhost:8080/api/item/**{itemId}**/assign/employee/**{employeeId}**
        * **{itemId}** - replace with id of target item
        * **{employeeId}** - replace with id of employee to be assigned
6. Assign Location
    * POST Method
    * localhost:8080/api/item/**{itemId}**/assign/employee/**{locationId}**
        * **{itemId}** - replace with id of target item
        * **{locationId}** - replace with id of location to be assigned

#### Location
1. See All
    * GET Method
    * localhost:8080/api/location
2. Add New
    * POST Method
    * localhost:8080/api/location
    * Requires location information in request body
3. Update Existing
    * PUT Method
    * localhost:8080/api/location/**{id}**
        * **{id}** - replace with id of location to edit
    * Requires location information in request body
4. Delete Existing
    * DELETE Method
    * localhost:8080/api/location/**{id}**
        * **{id}** - replace with id of location to delete

#### Request
1. See All
    * GET Method
    * localhost:8080/api/request
2. Add New
    * POST Method
    * localhost:8080/api/request
    * Requires request information in request body
3. Update Existing
    * PUT Method
    * localhost:8080/api/request/**{id}**
        * **{id}** - replace with id of request to edit
    * Requires request information in request body
4. Delete Existing
    * DELETE Method
    * localhost:8080/api/request/**{id}**
        * **{id}** - replace with id of request to delete
5. Assign Employee
    * POST Method
    * localhost:8080/api/request/**{requestId}**/assign/employee/**{employeeId}**
        * **{requestId}** - replace with id of target request
        * **{employeeId}** - replace with id of employee to be assigned
6. Assign Item
    * POST Method
    * localhost:8080/api/request/**{requestId}**/assign/employee/**{locationId}**
        * **{requestId}** - replace with id of target request
        * **{locationId}** - replace with id of location to be assigned