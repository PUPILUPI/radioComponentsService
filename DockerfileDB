FROM neo4j:latest

# Обновить и установить необходимые пакеты
RUN apt-get update && apt-get install -y locales

# Настроить локаль
RUN locale-gen en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF-8
