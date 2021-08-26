export default class ErrorCodeGenerator {

    /**
     * [ Read the input error and return an adapted HTTP error code, based on the error.name ]
     * @param error [the error message to read]
     * @returns     [adapted error code]
     */
    public readError(error: Error):number {
        if(error.name == "QueryFailedError")
        {
            return 400
        }
        else if (error.name == "NotFoundError")
        {
            return 404
        }
        return 500
    }
}
