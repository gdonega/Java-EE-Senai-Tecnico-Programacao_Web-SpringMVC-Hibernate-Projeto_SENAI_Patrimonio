var isPressed = false;
window.addEventListener('load', () => {

    document.querySelectorAll("*[data-trigger-dialog]").forEach((e) => {
        e.addEventListener('click', () => {
            let id = e.getAttribute('data-trigger-dialog');

            

            if(isPressed){
                
                document.querySelector("#" + id).classList.remove('show');	
                
            }else{

                document.querySelector("#" + id).classList.toggle('show');
            }
            
            
        
        })
    });

});