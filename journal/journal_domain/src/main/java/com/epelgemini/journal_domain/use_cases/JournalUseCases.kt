package com.epelgemini.journal_domain.use_cases

data class JournalUseCases(
    val createJournal: CreateJournal,
    val updateJournal: UpdateJournal,
    val updateWriting: UpdateWriting,
    val deleteJournal: DeleteJournal,
    val getJournals: GetJournals
)