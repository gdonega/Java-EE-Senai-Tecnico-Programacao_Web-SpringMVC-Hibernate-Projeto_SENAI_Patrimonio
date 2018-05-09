window.addEventListener('load', () => {
let jaViuAPagina = localStorage.getItem("jaViu");
    if(!jaViuAPagina){
        localStorage.setItem("jaViu", true);
    }else{
        //Desabilita o dialog
        document.querySelectorAll(".dialog").forEach((e) => {
            e.classList.add("d-none");
        });
    }
    
});