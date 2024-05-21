<script>
    import Header from '../lib/components/Header.svelte';
    import Footer from '../lib/components/Footer.svelte';
    import './styles.css';
  </script>
  
  <div class="min-h-screen flex flex-col">
    <Header />
    <main class="flex-grow container mx-auto p-4">
      <slot />
    </main>
    <Footer />
  </div>
