<script>
    import { createEventDispatcher } from "svelte";
  
    const dispatcher = createEventDispatcher();
  
    let userDetails = {
      username: "john_doe",
      email: "john@example.com",
      firstName: "John",
      lastName: "Doe"
    };
  
    let editMode = false;
  
    function toggleEditMode() {
      editMode = !editMode;
    }
  
    function handleSave() {
      // Add your save logic here
      console.log("User details saved:", userDetails);
      editMode = false;
    }
  
    function handleDelete() {
      if (confirm("Are you sure you want to delete your account? This action cannot be undone.")) {
        // Add your delete logic here
        console.log("Account deleted");
        dispatcher("accountDeleted");
      }
    }
  
    function handleDownloadData() {
      // Add logic to download user data
      console.log("User data downloaded");
    }
  
    function handleRequestDataDeletion() {
      if (confirm("Are you sure you want to request data deletion? This action cannot be undone.")) {
        // Add logic to request data deletion
        console.log("Data deletion requested");
      }
    }
  </script>
  
  <div class="p-6 bg-base-100 rounded-lg shadow-md">
    <h2 class="text-2xl font-bold mb-4">Account Details</h2>
  
    <div class="mb-4">
      <label class="label" for="username-input">
        <span class="label-text">Username</span>
      </label>
      <input
        type="text"
        bind:value={userDetails.username}
        class="input input-bordered w-full"
        disabled={!editMode}
        id="username-input"
      />
    </div>
  
    <div class="mb-4">
      <label class="label" for="email-input">
        <span class="label-text">Email</span>
      </label>
      <input
        type="email"
        bind:value={userDetails.email}
        class="input input-bordered w-full"
        disabled={!editMode}
        id="email-input"
      />
    </div>
  
    <div class="mb-4">
      <label class="label" for="first-name-input">
        <span class="label-text">First Name</span>
      </label>
      <input
        type="text"
        bind:value={userDetails.firstName}
        class="input input-bordered w-full"
        disabled={!editMode}
        id="first-name-input"
      />
    </div>
  
    <div class="mb-4">
      <label class="label" for="last-name-input">
        <span class="label-text">Last Name</span>
      </label>
      <input
        type="text"
        bind:value={userDetails.lastName}
        class="input input-bordered w-full"
        disabled={!editMode}
        id="last-name-input"
      />
    </div>
  
    {#if editMode}
      <button class="btn btn-primary mr-2" on:click={handleSave}>Save</button>
      <button class="btn" on:click={toggleEditMode}>Cancel</button>
    {:else}
      <button class="btn btn-primary mr-2" on:click={toggleEditMode}>Edit</button>
      <button class="btn btn-error" on:click={handleDelete}>Delete Account</button>
    {/if}
  
    <h3 class="text-xl font-bold mt-6 mb-4">GDPR Compliance</h3>
    <div class="mb-4">
      <p class="mb-2">As part of our commitment to GDPR compliance, you have the following rights:</p>
      <ul class="list-disc list-inside">
        <li>Right to access your data</li>
        <li>Right to rectify your data</li>
        <li>Right to delete your data</li>
        <li>Right to restrict processing</li>
        <li>Right to data portability</li>
        <li>Right to object to processing</li>
      </ul>
    </div>
  
    <div class="mb-4">
      <button class="btn btn-secondary mr-2" on:click={handleDownloadData}>Download Your Data</button>
      <button class="btn btn-error" on:click={handleRequestDataDeletion}>Request Data Deletion</button>
    </div>
  </div>
  