provider "aws" {
  version = "3.0.0"
  profile = "terraform-demo"
  region  = "eu-central-1"
}

resource "aws_cloudwatch_metric_alarm" "winr_dynamodb_failure" {
  alarm_name        = "Winr DynamoDB Failure"
  alarm_description = "DynamoDB connection failure alarm for winr"
  namespace         = "TASTR"
  dimensions = {
    "Service" : "winr"
  }
  metric_name         = "ConnectionFailure"
  statistic           = "SampleCount"
  period              = "60"
  comparison_operator = "GreaterThanOrEqualToThreshold"
  threshold           = "5"
  evaluation_periods  = "1"
  treat_missing_data  = "notBreaching"
}