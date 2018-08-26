export class Ng2ModalConfig{
    public button1: boolean = false;
    public button2: boolean = false;
    public button1Text: string = "";
    public button2Text: string = "";

    constructor(
        public title: string = "Modal Title",
        public id: string = "MyModal",
        public acceptButton: boolean = true,
        public cancelButton: boolean = true,
        public acceptButtonText: string = "Accept",
        public cancelButtonText: string = "Cancel"
    ){}
    
}