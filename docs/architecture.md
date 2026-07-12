# NewsFeed Architecture

## Overview
Purpose: ingest posts, propagate via async fan-out, and serve low-latency precomputed feeds.

## High-Level Diagram
(Insert diagram link or ASCII diagram)

## Services
- API Service (Spring Boot) — endpoints: POST /posts, POST /follow, GET /feed
- Fan-Out Coordinator (Kafka consumer) — consumes post-created, pages followers, publishes fanout chunks
- Fan-Out Workers (Kafka consumers) — consume chunks, write to precomputedFeed

## Data Flow
Client → POST /posts -> API writes Post to DynamoDB -> API publishes PostCreated to Kafka -> Fan-Out Coordinator -> FanOutChunk events -> Fan-Out Workers -> write to precomputedFeed

## Persistence
Tables:
- posts (PK: postId) — creatorId, content, createdAt
- follows (PK: userFollowing, SK: userFollowed) — reverse GSI for followers
- precomputedFeed (PK: userId, SK: createdAt#postId) — postId, creatorId

## Messaging
Topics: post-created, fanout-chunks. Partition by creatorId or postId. Consumer groups for coordinator/workers. Plan for retries and DLQ.

## Scaling Model
Chunk followers into batches (~500) to prevent single-message bottlenecks. Horizontal worker scaling.

## Observability
Metrics: fanout_latency, kafka_lag, feed_read_latency, worker_errors. Dashboards: Prometheus -> Grafana.

## Security
Auth design, secrets storage, and internal endpoint access rules (document separately).
