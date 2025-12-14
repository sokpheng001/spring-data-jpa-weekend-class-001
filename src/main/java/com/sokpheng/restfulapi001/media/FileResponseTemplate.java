package com.sokpheng.restfulapi001.media;

import lombok.Builder;

@Builder
public record FileResponseTemplate(
    String fileName,
    Long size,
    String type,
    String previewUrl,
    String downloadUrl
) { }
