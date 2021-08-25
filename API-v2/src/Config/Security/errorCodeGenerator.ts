export default class ErrorCodeGenerator {

    public readError(error: Error):number {
        if(error.name == "QueryFailedError")
            return 400
        else if (error.name == "NotFoundError")
            return 404
        return 500
    }
}
