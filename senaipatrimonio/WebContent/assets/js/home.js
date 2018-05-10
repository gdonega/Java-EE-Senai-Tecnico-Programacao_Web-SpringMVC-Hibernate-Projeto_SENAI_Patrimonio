window.addEventListener('load', () => {
    let jaViuAPagina = localStorage.getItem("jaViu");
        if(!jaViuAPagina){
            localStorage.setItem("jaViu", true);
            document.querySelectorAll(".dialog").forEach((e) => {
                e.classList.remove("d-none");
            });
        }
    });