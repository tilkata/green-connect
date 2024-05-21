<script>
    import { onMount } from 'svelte';
    import { writable } from 'svelte/store';
  
    let email = '';
    let password = '';
    let errorMessage = writable('');
  
    function handleLogin() {
      if (!email || !password) {
        errorMessage.set('Please enter both email and password.');
        return;
      }
      // Simulate login logic
      alert('Login successful!');
      errorMessage.set('');
      // Redirect to feed page after successful login
      window.location.href = '/feed';
    }
  </script>
  
  <svelte:head>
    <title>Login - GreenConnect</title>
    <meta name="description" content="Login to GreenConnect, your platform for promoting actions towards achieving the 17 Sustainable Development Goals." />
  </svelte:head>
  
  <div class="flex justify-center items-center min-h-screen bg-green-100">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-sm">
      <h2 class="text-3xl font-bold mb-6 text-center text-green-700">Login to GreenConnect</h2>
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
      {#if $errorMessage}
        <p class="text-red-500 text-center mb-4">{$errorMessage}</p>
      {/if}
      <button class="btn bg-green-700 hover:bg-green-600 text-white w-full mt-4" on:click={handleLogin}>Login</button>
      <p class="mt-4 text-center text-green-700">
        Don't have an account? <a class="link link-primary text-green-700 hover:underline" href="/register">Register</a>
      </p>
      <p class="mt-2 text-center text-green-700">
        <a class="link link-primary text-green-700 hover:underline" href="/forgot-password">Forgot Password?</a>
      </p>
    </div>
  </div>
  