<script>
    export let consent = false;
  </script>
  
  <div class="form-control mb-4">
    <label class="cursor-pointer flex items-center">
      <input type="checkbox" bind:checked={consent} class="checkbox" />
      <span class="label-text ml-2">I consent to the storage of my data in accordance with the privacy policy</span>
    </label>
  </div>
  