describe('Registration Page', () => {
  beforeEach(() => {
    cy.visit('http://localhost:5173/register');
  });

  it('should display registration form', () => {
    cy.get('h2').contains('Register for GreenConnect');
    cy.get('input[placeholder="Enter your email"]').should('be.visible');
    cy.get('input[placeholder="Enter your password"]').should('be.visible');
    cy.get('input[placeholder="Confirm your password"]').should('be.visible');
    cy.get('button').contains('Register').should('be.visible');
  });

  it('should show error if passwords do not match', () => {
    cy.get('input[placeholder="Enter your email"]').type('peppa@pig.com');
    cy.get('input[placeholder="Enter your password"]').type('porkchop123');
    cy.get('input[placeholder="Confirm your password"]').type('porkchop1234');
    cy.get('input[type="checkbox"]').check();
    cy.get('button').contains('Register').click();
    cy.get('.text-red-500', { timeout: 10000 }).should('contain', 'Passwords do not match.');
});


  it('should show error if consent is not given', () => {
    cy.get('input[placeholder="Enter your email"]').type('peppa@pig.com');
    cy.get('input[placeholder="Enter your password"]').type('porkchop123');
    cy.get('input[placeholder="Confirm your password"]').type('porkchop123');
    cy.get('button').contains('Register').click();
    cy.get('.text-red-500').should('contain', 'You need to consent to data storage to register.');
  });

  it('should register successfully with valid inputs', () => {
    cy.get('input[placeholder="Enter your email"]').type('peppa@pig.com');
    cy.get('input[placeholder="Enter your password"]').type('porkchop123');
    cy.get('input[placeholder="Confirm your password"]').type('porkchop123');
    cy.get('input[type="checkbox"]').check();
    cy.get('button').contains('Register').click();
    cy.on('window:alert', (str) => {
      expect(str).to.equal('Register button clicked');
    });
    cy.url().should('include', '/login');
  });
});
