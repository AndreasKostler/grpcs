package commbank.grpcs

object Service {
  type Service[Req, Rep] = Req ⇒ scala.concurrent.Future[Rep]
}
