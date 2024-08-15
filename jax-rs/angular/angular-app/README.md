# AngularApp

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.0.5.

To create an Angular application make sure Node.js is installed on your system. Check if node.js is successfully installed or not by typing the following command.

node -v 

3.1. Installing Angular
To install Angular we need to install Angular-CLI. Angular-CLI is a command-line interface used to initialize, develop and maintain Angular applications. Install or update Angular CLI by typing the following command:

npm install -g @angular/cli

ng new angular-app

When prompted, select YES to add the angular routing module. We are using the CSS for stylesheets.

This command generates a TypeScript class named Customer and saves it in a file called customer.model.ts. This class is typically used to define the structure of a data object, like a customer entity in your application.

ng g class Customer --type=model

This command generates a new service class called CustomerService and saves it in a file named customer.service.ts within the services directory. The service typically contains methods for interacting with a backend API, such as creating, reading, updating, or deleting customer data.

ng g s services/customer

This command generates a new component called CustomerListComponent. This component will typically be used to display a list of customers. The command will generate the corresponding .ts, .html, .css, and .spec.ts files.

ng g c customer-list

This command generates a new component called CustomerCreateComponent. This component is typically used to handle the creation of a new customer, usually by presenting a form to the user. The command will generate the corresponding .ts, .html, .css, and .spec.ts files.

ng g c customer-create

This command generates a new component called CustomerUpdateComponent. This component is usually used to manage the updating of existing customer data, again typically via a form. The corresponding files will be created in the project.

ng g c customer-update

ng serve --o


npm update


