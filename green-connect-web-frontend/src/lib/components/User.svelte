<script>
    export let user;

    function getRandomPastelColor() {
        // List of pastel colors
        const pastelColors = [
            '#FFB3BA', // light pink
            '#FFDFBA', // light orange
            '#FFFFBA', // light yellow
            '#BAFFC9', // light green
            '#BAE1FF', // light blue
            '#D7B0FF', // light purple
            '#FFDAC1', // light peach
            '#B5EAD7', // light mint
            '#F7CEC7', // light coral
            '#E2F0CB'  // light lime
            // Add more pastel colors as needed
        ];

        // Select a random index from the pastelColors array
        const randomIndex = Math.floor(Math.random() * pastelColors.length);

        // Return the random pastel color
        return pastelColors[randomIndex];
    }
</script>

<div class="flex items-center mb-4">
    <div class="w-10 h-10 rounded-full mr-3 flex items-center justify-center" style="background-color: {getRandomPastelColor()};">
        <p class="font-bold text-white text-lg center">{user.fullName.charAt(0).toUpperCase()}</p>
    </div>
    <div>
        <p class="font-bold">@{user.username}</p>
        <p class="text-gray-600 text-sm">{user.fullName}</p>
    </div>
</div>
