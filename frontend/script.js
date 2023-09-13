document.addEventListener("DOMContentLoaded", function () {
    const codeInput = document.getElementById("codeInput");
    const targetLanguage = document.getElementById("targetLanguage");
    const convertButton = document.getElementById("convertButton");
    const debugButton = document.getElementById("debugButton");
    const qualityButton = document.getElementById("qualityButton");
    const output = document.getElementById("output");

    convertButton.addEventListener("click", function () {
        const code = codeInput.value;
        const selectedLanguage = targetLanguage.value;
        performAction("convert", code, selectedLanguage);
    });

    debugButton.addEventListener("click", function () {
        const code = codeInput.value;
        performAction("debug", code);
    });

    qualityButton.addEventListener("click", function () {
        const code = codeInput.value;
        performAction("quality", code);
    });

    function performAction(action, code, targetLanguage) {
        const requestData = {
            code: code,
            targetLanguage: targetLanguage,
        };

        fetch(`/api/${action}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(requestData),
        })
            .then((response) => response.json())
            .then((data) => {
                output.innerHTML = data.output;
            })
            .catch((error) => {
                console.error("Error:", error);
                output.innerHTML = "An error occurred while processing the request.";
            });
    }
});
