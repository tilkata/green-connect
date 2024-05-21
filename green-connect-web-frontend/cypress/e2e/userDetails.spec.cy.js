/// <reference types="cypress" />

describe('Account Details Page', () => {
    beforeEach(() => {
      cy.visit('http://localhost:5173/userDetails');
    });
  
    it('should display user details correctly', () => {
      cy.get('#username-input').should('have.value', 'john_doe');
      cy.get('#email-input').should('have.value', 'john@example.com');
      cy.get('#first-name-input').should('have.value', 'John'); // First Name input
      cy.get('#last-name-input').should('have.value', 'Doe'); // Last Name input
    });
  
    it('should enable editing mode', () => {
      cy.contains('button', 'Edit').click();
      cy.get('#username-input').should('not.be.disabled');
      cy.get('#email-input').should('not.be.disabled');
      cy.get('#first-name-input').should('not.be.disabled'); // First Name input
      cy.get('#last-name-input').should('not.be.disabled'); // Last Name input
    });
  
    it('should save edited user details', () => {
      cy.contains('button', 'Edit').click();
      cy.get('#username-input').clear().type('jane_doe');
      cy.get('#email-input').clear().type('jane@example.com');
      cy.get('#first-name-input').clear().type('Jane'); // First Name input
      cy.get('#last-name-input').clear().type('Doe'); // Last Name input
      cy.contains('button', 'Save').click();
      cy.get('#username-input').should('have.value', 'jane_doe');
      cy.get('#email-input').should('have.value', 'jane@example.com');
      cy.get('#first-name-input').should('have.value', 'Jane'); // First Name input
      cy.get('#last-name-input').should('have.value', 'Doe'); // Last Name input
    });
  
    it('should cancel editing mode', () => {
      cy.contains('button', 'Edit').click();
      cy.get('#username-input').clear().type('jane_doe');
      cy.contains('button', 'Cancel').click();
      cy.get('#username-input').should('have.value', 'john_doe');
      cy.get('#email-input').should('have.value', 'john@example.com');
      cy.get('#first-name-input').should('have.value', 'John'); // First Name input
      cy.get('#last-name-input').should('have.value', 'Doe'); // Last Name input
    });
  
    it('should handle account deletion', () => {
      cy.contains('button', 'Delete Account').click();
      cy.on('window:confirm', () => true);
      cy.contains('Account deleted').should('exist');
    });
  
    it('should download user data', () => {
      cy.contains('button', 'Download Your Data').click();
      cy.contains('User data downloaded').should('exist');
    });
  
    it('should request data deletion', () => {
      cy.contains('button', 'Request Data Deletion').click();
      cy.on('window:confirm', () => true);
      cy.contains('Data deletion requested').should('exist');
    });
  });
  