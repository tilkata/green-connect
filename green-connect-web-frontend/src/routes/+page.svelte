<script>
    import logo from '$lib/assets/logo.png';
  
    const goals = [
      { id: 1, title: "No Poverty", link: "https://sdgs.un.org/goals/goal1", icon: "🌍", color: "bg-red-500" },
      { id: 2, title: "Zero Hunger", link: "https://sdgs.un.org/goals/goal2", icon: "🌾", color: "bg-yellow-500" },
      { id: 3, title: "Good Health and Well-being", link: "https://sdgs.un.org/goals/goal3", icon: "❤️", color: "bg-green-500" },
      { id: 4, title: "Quality Education", link: "https://sdgs.un.org/goals/goal4", icon: "📚", color: "bg-red-600" },
      { id: 5, title: "Gender Equality", link: "https://sdgs.un.org/goals/goal5", icon: "♀️", color: "bg-orange-500" },
      { id: 6, title: "Clean Water and Sanitation", link: "https://sdgs.un.org/goals/goal6", icon: "🚰", color: "bg-blue-500" },
      { id: 7, title: "Affordable and Clean Energy", link: "https://sdgs.un.org/goals/goal7", icon: "💡", color: "bg-yellow-400" },
      { id: 8, title: "Decent Work and Economic Growth", link: "https://sdgs.un.org/goals/goal8", icon: "💼", color: "bg-red-700" },
      { id: 9, title: "Industry, Innovation and Infrastructure", link: "https://sdgs.un.org/goals/goal9", icon: "🏗️", color: "bg-orange-600" },
      { id: 10, title: "Reduced Inequality", link: "https://sdgs.un.org/goals/goal10", icon: "⚖️", color: "bg-purple-500" },
      { id: 11, title: "Sustainable Cities and Communities", link: "https://sdgs.un.org/goals/goal11", icon: "🏙️", color: "bg-yellow-600" },
      { id: 12, title: "Responsible Consumption and Production", link: "https://sdgs.un.org/goals/goal12", icon: "♻️", color: "bg-green-600" },
      { id: 13, title: "Climate Action", link: "https://sdgs.un.org/goals/goal13", icon: "🌍", color: "bg-green-700" },
      { id: 14, title: "Life Below Water", link: "https://sdgs.un.org/goals/goal14", icon: "🐠", color: "bg-blue-700" },
      { id: 15, title: "Life on Land", link: "https://sdgs.un.org/goals/goal15", icon: "🌲", color: "bg-green-800" },
      { id: 16, title: "Peace and Justice Strong Institutions", link: "https://sdgs.un.org/goals/goal16", icon: "🕊️", color: "bg-blue-800" },
      { id: 17, title: "Partnerships to achieve the Goal", link: "https://sdgs.un.org/goals/goal17", icon: "🤝", color: "bg-blue-900" }
    ];
  </script>
  
  <div class="container mx-auto p-6">
    <div class="flex flex-col items-center mb-10">
      <img src={logo} alt="GreenConnect Logo" class="w-40 h-40 mb-4">
      <h1 class="text-5xl font-bold mb-4">Welcome to GreenConnect</h1>
      <p class="text-xl text-center mb-6 max-w-2xl">
        GreenConnect is a social media platform dedicated to promoting actions towards achieving the 17 Sustainable Development Goals (SDGs). Join our community to connect with like-minded individuals and make a positive impact on our planet.
      </p>
      
      <button class="btn btn-primary mb-6">Join Now</button>
    </div>
  
    <div class="bg-green-100 p-6 rounded-lg shadow-lg mb-10">
      <h2 class="text-3xl font-bold mb-4">Our Mission</h2>
      <p class="text-lg text-center mb-6 max-w-2xl mx-auto">
        At GreenConnect, we believe in the power of community to drive sustainable change. Our platform empowers users to share ideas, collaborate on projects, and inspire action towards a better, more sustainable future.
      </p>
    </div>
  
    <div class="bg-white p-6 rounded-lg shadow-lg">
      <h2 class="text-3xl font-bold mb-4 text-center">The 17 Sustainable Development Goals</h2>
      <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {#each goals as goal}
          <a href={goal.link} target="_blank" class="card shadow-md hover:shadow-lg transition-shadow duration-300">
            <div class="card-body items-center text-center">
              <div class={`w-12 h-12 rounded-full ${goal.color} text-white flex items-center justify-center mb-2 text-2xl`}>
                {goal.icon}
              </div>
              <p class="font-bold">{goal.title}</p>
              <p class="text-sm">`#{goal.id}`</p>
            </div>
          </a>
        {/each}
      </div>
    </div>
  </div>
  
  