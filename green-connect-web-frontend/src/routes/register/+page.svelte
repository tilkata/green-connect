<script>
  import ConsentCheckbox from '$lib/components/ConsentCheckbox.svelte';
  import { writable } from 'svelte/store';

  let email = '';
  let password = '';
  let confirmPassword = '';
  let consent = false;
  let errorMessage = writable('');

  function handleRegister() {
    if (password !== confirmPassword) {
      errorMessage.set('Passwords do not match.');
      return;
    }
    if (!consent) {
      errorMessage.set('You need to consent to data storage to register.');
      return;
    }
    alert('Register button clicked');
    errorMessage.set('');
    // Redirect to login page after successful registration
    window.location.href = '/login';
  }
</script>

<svelte:head>
  <title>Register - GreenConnect</title>
  <meta name="description" content="Register for GreenConnect, your platform for promoting actions towards achieving the 17 Sustainable Development Goals." />
</svelte:head>

<div class="flex justify-center items-center min-h-screen bg-green-100">
  <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-sm">
    <h2 class="text-3xl font-bold mb-6 text-center text-green-700">Register for GreenConnect</h2>
    <div class="form-control mb-4">
      <label class="label" for="email">
        <span class="label-text text-green-700">Email</span>
      </label>
      <input type="email" bind:value={email} class="input input-bordered border-green-500" placeholder="Enter your email" />
    </div>
    <div class="form-control mb-4">
      <label class="label" for="password">
        <span class="label-text text-green-700">Password</span>
      </label>
      <input type="password" bind:value={password} class="input input-bordered border-green-500" placeholder="Enter your password" />
    </div>
    <div class="form-control mb-4">
      <label class="label" for="confirmPassword">
        <span class="label-text text-green-700">Confirm Password</span>
      </label>
      <input type="password" bind:value={confirmPassword} class="input input-bordered border-green-500" placeholder="Confirm your password" />
    </div>
    <ConsentCheckbox bind:consent={consent} />
    {#if $errorMessage}
      <p class="text-red-500 text-center mb-4">{$errorMessage}</p>
    {/if}
    <button class="btn bg-green-700 hover:bg-green-600 text-white w-full mt-4" on:click={handleRegister}>Register</button>
    <p class="mt-4 text-center text-green-700">
      Already have an account? <a class="link link-primary text-green-700 hover:underline" href="/login">Login</a>
    </p>
  </div>
</div>
